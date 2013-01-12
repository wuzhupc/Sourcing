//
//  BaseVO.m
//  sourcing
//
//  Created by wuzhu on 12-12-25.
//  Copyright (c) 2012年 wuzhu. All rights reserved.
//

#import "BaseVO.h"
#import "HtmlUtil.h"

@implementation BaseVO

@synthesize Id = id_;

- (BOOL) isEqual:(id)object
{
    if(object == nil)
        return  NO;
    if ([object isMemberOfClass:[self class]])
    {
        BaseVO *other = (BaseVO *)object;
        return other.Id == self.Id;
    }
    return [super isEqual: object];
}

-(NSString *)getHtmlTitle
{
    return [HtmlUtil getHtmlHead];
}
-(NSString *)getHtmlSubTitle
{
    return @"";
}
-(void)setHtmlToShow:(DetailInfoCompleteBlock)kcomblock failure:(DetailInfoErrorBlock)kerrorblock
{
    _comblock = kcomblock;
    _errorblock = kerrorblock;
}
-(NSString *)generateShareText
{
    return @"";
}
-(NSString *)getHtmlContext
{
    return @"";
}
-(CGFloat)heightForCell:(NSInteger)kindex
{
    return 80.0f;
}

-(UITableViewCell *)tableView:(UITableView *)tableView index:(NSInteger)kindex
{
    return [BaseVO tableViewWithEmptyData:tableView title:@""];
}

+(UITableViewCell *)tableViewWithEmptyData:(UITableView *)tableView title:(NSString *)ktitle
{
    static NSString *CellIdentifier = @"EmptyData";
    UITableViewCell *cell = (UITableViewCell*)[tableView dequeueReusableCellWithIdentifier:CellIdentifier];
    if (!cell)
    {
        cell = [[UITableViewCell alloc] initWithStyle:UITableViewCellStyleDefault reuseIdentifier:CellIdentifier];
        [cell setSelectionStyle:UITableViewCellSelectionStyleNone];
        [cell.textLabel setFont:[UIFont boldSystemFontOfSize:17]];
        cell.textLabel.textAlignment = UITextAlignmentCenter;
    }
    [cell.textLabel setText:ktitle];
    return cell;
}

@end
