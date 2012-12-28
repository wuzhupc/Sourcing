//
//  BaseJsonService.m
//  EmbaClientForiPhone
//
//  Created by wuzhu on 12-6-28.
//  Copyright (c) 2012年 __MyCompanyName__. All rights reserved.
//

#import "BaseJsonService.h"
#import "StringUtil.h"
#import "NetWorkUtil.h"
#import "DataInterfaceUtil.h"
#import "ASIFormDataRequest.h"
#import "ResponseVO.h"
#import "UIDevice+IdentifierAddition.h"

@implementation BaseJsonService


#define CBOOL_DEBUG_JOSN YES
//是否读取本地assets
#define CBOOL_READASSETS YES

-(void) getData:(NSString *) json delegate:(id)delegate  tag:(NSInteger)mtag
{
    [self getData:json url:nil delegate:delegate process:NO processcontent:nil tag:mtag];
}

-(void) getData:(NSString *) json url:(NSString *)url delegate:(id)delegate tag:(NSInteger)mtag
{
    [self getData:json url:url delegate:delegate process:NO processcontent:nil tag:mtag];
}

-(void) getData:(NSString *) json url:(NSString *)url delegate:(id)delegate process:(BOOL)showprocess tag:(NSInteger)mtag
{
    [self getData:json url:url delegate:delegate process:showprocess processcontent:nil tag:mtag];
}

/**
 * 获取数据
 * @param json　请求报文
 * @param url　地址
 * @param delegate 处理获取数据结果的对象，需要包括BsseServiceDelegate ASIHTTPRequestDelegate中的处理方法
 * @param showprogress 显示处理进度
 * @param progressshowcontent	显示处理进度提示内容，如果为null，显示默认提示
 * @param tag 标志位
 */
-(void) getData:(NSString *)mjson url:(NSString *)murlstr delegate:(id)mdelegate process:(BOOL)mshowprocess processcontent:(NSString *)mshowprocesscontent tag:(NSInteger)mtag
{
    if([StringUtil isEmptyStr:mjson]||mdelegate==nil)
        return;
    _delegate = mdelegate;
    _tag  = mtag;
    if(CBOOL_DEBUG_JOSN)
        NSLog(@"BaseJsonService:%@",mjson);
    if (![NetWorkUtil checkNetWork:NO]) 
    {
        if (CBOOL_DEBUG_JOSN) {
            NSLog(@"BaseJsonService:%@",NSLocalizedString(@"Currently unable to access the server.", @"提示无法连接服务器"));
        }
        [BaseJsonService sendServiceFailInfo:mdelegate msg:NSLocalizedString(@"Currently unable to access the server.", @"提示无法连接服务器") tag:mtag];
        return;
    }
    
    //TODO 加载本地文件
    if(CBOOL_READASSETS)
    {
        
    }
    NSURL *url;
    if([StringUtil isEmptyStr:murlstr])
        url = [NSURL URLWithString:[DataInterfaceUtil getServerUrl]];
    else {
        url = [NSURL URLWithString:murlstr];
    }
    ASIFormDataRequest *request = [ASIFormDataRequest requestWithURL:url];
    [request setTag:mtag];
    [request setPostValue:mjson forKey:@"requestjson"];
    [request setDelegate:self];
    [request setRequestMethod:@"POST"];
    [request setTimeOutSeconds:20];
    [request startAsynchronous];
    //TODO 增加显示处理进度
}

-(void)requestFailed:(ASIHTTPRequest *)request
{
    //TODO
}

-(void)requestFinished:(ASIHTTPRequest *)request
{
    //TODO
}

-(void)setAssetsFileInfo:(NSString *)kcommandName suffix:(NSString *)ksuffix
{
    _commandName = kcommandName;
    _suffix = ksuffix;
}

+(NSString *)getDevID
{
    return [[UIDevice currentDevice] uniqueDeviceIdentifier];
    //[[UIDevice currentDevice] uniqueGlobalDeviceIdentifier]
}

/**
 *
 */
+(void) sendServiceFailInfo:(id)mdelegate msg:(NSString*)msgconent tag:(NSInteger)mtag
{
    if(!mdelegate) return;
    if ([mdelegate respondsToSelector:@selector(serviceResult:)])
    {
        ResponseVO *vo = [[ResponseVO alloc] initWithResult:0 msg:msgconent tag:mtag];
        [mdelegate performSelector:@selector(serviceResult:) withObject:vo];
    }
}

@end
