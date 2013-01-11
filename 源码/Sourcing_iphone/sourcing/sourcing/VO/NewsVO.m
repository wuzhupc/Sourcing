//
//  NewsVO.m
//  sourcing
//
//  Created by wuzhu on 12-12-26.
//  Copyright (c) 2012年 wuzhu. All rights reserved.
//

#import "NewsVO.h"
#import "StringUtil.h"
#import "HtmlUtil.h"
#import "MobileInfoService.h"
#import "ResponseVO.h"
#import "JsonParser.h"
#import "NewsDetailVO.h"

@implementation NewsVO

@synthesize headline = headline;
@synthesize newssummary = newssummary;
@synthesize title = title;
@synthesize titlepic = titlepic;
@synthesize newstype = newstype;
@synthesize titlepic_small = titlepic_small;
@synthesize publishtime = publishtime;
@synthesize auther = auther;
@synthesize source = source;
@synthesize readernum = readernum;
@synthesize commentnum = commentnum;

-(void)setNewsid:(NSInteger )kid
{
    newsid = kid;
    _id = kid;
}
-(NSInteger )Newsid
{
    return newsid;
}

#pragma mark - public methods

-(BOOL)isHeadline
{
    if([StringUtil isEmpty:headline])
        return NO;
    return [headline isEqualToString:@"1"] || [[headline lowercaseString] isEqualToString:@"true"];
}
#pragma mark - Superclass Overrides
-(NSString *)getHtmlTitle
{
    return [NSString stringWithFormat:@"<br/><div align=\"center\"><font color=\"#111111\" size=\"4pt\"><strong>%@</strong></font></div><br/>",title];
}
-(NSString *)getHtmlSubTitle
{
    return [NSString stringWithFormat:@"<div align=\"center\"><font color=\"#666666\" size=\"2pt\">%@&nbsp;&nbsp; 来源:%@</font></div><div style=\"height:0;border-bottom:1px solid #f00\"></div>",publishtime,source];
}

-(void)setHtmlToShow:(DetailInfoCompleteBlock)kcomblock failure:(DetailInfoErrorBlock)kerrorblock
{
    [super setHtmlToShow:kcomblock failure:kerrorblock];
    MobileInfoService *service = [[MobileInfoService alloc] initWithDelegate:self tag:CINT_TAG_GETNEWSDETAIL];
    service.tag2 = newsid;
    [service getNewsDetail:[NSString stringWithFormat:@"%d",newstype] newsid:[NSString stringWithFormat:@"%d",newsid]];
    
}
-(NSString *)generateShareText
{
    return title;
}

#pragma mark - BaseServiceDelegate
-(void)serviceResult:(ResponseVO *)result
{
    if(result.tag==CINT_TAG_GETNEWSDETAIL&&result.tag2==newsid)
    {
        if ([result isSucess]) {
            ResponseVO *rvo = [[ResponseVO alloc] init];
            NewsDetailVO *vo = [JsonParser parseJsonToEntity:result.msg respVO:&rvo ref:nil];
            if([rvo isSucess])
            {
                if(_comblock) _comblock(vo);
            }
            else
            {
                if(_errorblock) _errorblock(rvo.msg);
            }
        }
        else
        {
            if(_errorblock)
                _errorblock(result.msg);
        }
    }
}

@end
