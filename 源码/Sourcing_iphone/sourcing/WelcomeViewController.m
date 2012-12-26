//
//  WelcomeViewController.m
//  sourcing
//
//  Created by wuzhu on 12-12-25.
//  Copyright (c) 2012年 wuzhu. All rights reserved.
//

#import "WelcomeViewController.h"
#import "FileUtil.h"
#import "JsonParser.h"
#import "ChannelVO.h"

@interface WelcomeViewController ()

@end

@implementation WelcomeViewController

- (id)initWithNibName:(NSString *)nibNameOrNil bundle:(NSBundle *)nibBundleOrNil
{
    self = [super initWithNibName:nibNameOrNil bundle:nibBundleOrNil];
    if (self) {
        // Custom initialization
    }
    return self;
}

- (void)viewDidLoad
{
    [super viewDidLoad];
    // Do any additional setup after loading the view from its nib.
    
//    //TEST
//    NSString *channelinfo =  [FileUtil getAssetsFileContent:@"channelinfo" oftype:@"json"];
//    //NSLog(@"channelinfo.json:%@" ,channelinfo);
//    ResponseVO *resp = [[ResponseVO alloc] init];
//    NSArray *array = [JsonParser parseJsonToList:channelinfo respVO:&resp ref:nil];
//    if(array == nil||[array count]==0)
//        NSLog(@"转换失败");
//    else
//    {
//        ChannelVO *channelvo = [array objectAtIndex:1];
//        
//        NSLog(@"%d 第2个对象：id:%@ channelid:%@ channelname:%@",[array count],[channelvo Id],[channelvo ChannelID],[channelvo channelName]);
//    }
}

- (void)didReceiveMemoryWarning
{
    [super didReceiveMemoryWarning];
    // Dispose of any resources that can be recreated.
}

@end
